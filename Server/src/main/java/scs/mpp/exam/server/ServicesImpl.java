package scs.mpp.exam.server;

import scs.mpp.exam.model.*;
import scs.mpp.exam.repository.*;
import scs.mpp.exam.services.Observer;
import scs.mpp.exam.services.Services;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class ServicesImpl implements Services {

    private IPlayerRepository playerRepository;
    private IRoundRepository roundRepository;
    private IGameRepository gameRepository;
    private IWordRepository wordRepository;

    private List<String> categories;
    private List<Word> wordList;

    private Map<String, Observer> loggedUsers;
    private Map<String, Integer>  globalPoints;
    private Integer round;
    private String currentCategory;
    private String gameId;

    private List<Round> globalAnswers;
    private List<Round> answers;

    public ServicesImpl(IPlayerRepository playerRepository, IRoundRepository roundRepository, IGameRepository gameRepository, IWordRepository wordRepository) {
        this.playerRepository = playerRepository;
        this.roundRepository = roundRepository;
        this.gameRepository = gameRepository;
        this.wordRepository = wordRepository;
        loggedUsers = new HashMap<>();

        wordList = wordRepository.getWords();
        globalPoints = new HashMap<>();

        categories = wordList.stream().map(Word::getCategory).distinct().collect(Collectors.toList());

        answers = new ArrayList<>();
        globalAnswers = new ArrayList<>();
    }

    @Override
    public void login(String user, String password, Observer observer) throws Exception {
        if (loggedUsers.containsKey(user)) {
            throw new Exception("User already logged in");
        }

        Player player = playerRepository.checkUser(user, password);

        if (player == null) {
            throw new Exception("Player not found");
        }

        loggedUsers.put(user, observer);
        globalPoints.put(user, 0);

        if (loggedUsers.size() == 3) {
            round = 1;
            Random rnd = new Random();
            int c = (rnd.nextInt(categories.size()));
            currentCategory = categories.get(c);
            gameId = UUID.randomUUID().toString();

            loggedUsers.forEach((k, v) -> {
                try {
                    v.startGame(currentCategory);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public List<WordAnswer> getWord(String gameId, String category) {
        return roundRepository.getByGameId(gameId).stream().filter(x -> x.getCategory().equals(category)).map(x -> {
            WordAnswer wordAnswer = new WordAnswer();
            wordAnswer.setCategory(category);
            wordAnswer.setGameId(gameId);
            wordAnswer.setWord(x.getAnswer());
            return wordAnswer;
        }).collect(Collectors.toList());
    }

    @Override
    public List<GameCategory> getCategories(String gameId) {
        List<Round> gameCategories = roundRepository.getByGameId(gameId);

        List<GameCategory> result = new ArrayList<>();
        List<Integer> rounds = new ArrayList<>();

        for (Round round : gameCategories) {
            if (!rounds.contains(round.getRound())) {
                GameCategory gameCategory = new GameCategory();
                gameCategory.setGameId(gameId);
                gameCategory.setGameCategory(round.getCategory());
                result.add(gameCategory);
                rounds.add(round.getRound());
            }
        }

        return result;
    }


    @Override
    public void sendQuizResponse(Round round) {
        round.setRound(this.round);
        round.setGameId(gameId);

        answers.add(round);
        Map<String, Integer> current = new HashMap<>();

        if (answers.size() == 3) {
            answers.forEach(answer -> {
                Integer points = 0;
                answer.setCategory(currentCategory);

                if (answer.getAnswer().equals("") ||
                        wordList.stream().noneMatch(x -> x.getCategory().equals(currentCategory) && x.getWord().equals(answer.getAnswer()))) {
                    points += 0;
                }

                if (wordList.stream().anyMatch(x -> x.getCategory().equals(currentCategory) && x.getWord().equals(answer.getAnswer()))
                    && globalAnswers.stream().anyMatch(x -> x.getAnswer().equals(answer.getAnswer()) && x.getCategory().equals(currentCategory)) ) {
                    points += 2;
                }

                if (wordList.stream().anyMatch(x -> x.getCategory().equals(currentCategory) && x.getWord().equals(answer.getAnswer()))
                        && globalAnswers.stream().noneMatch(x -> x.getAnswer().equals(answer.getAnswer()) && x.getCategory().equals(currentCategory)) ) {
                    points += 5;
                }

                globalPoints.put(answer.getPlayerName(), globalPoints.get(answer.getPlayerName()) + points);
                current.put(answer.getPlayerName(), points);
                answer.setPoints(points);
                roundRepository.saveRound(answer);
            });

            globalAnswers.addAll(answers);
            answers.clear();
            if (this.round == 5) {
                List<Map.Entry<String, Integer>> sortedMap = globalPoints.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toList());
                List<String> result = new ArrayList<>();

                for (Map.Entry<String, Integer> data : sortedMap) {
                    result.add(data.getKey());
                    result.add(data.getValue().toString());
                }

                Game game = new Game();
                game.setId(gameId);
                game.setFirstPlayer(sortedMap.get(0).getKey());
                game.setFirstPlayerPoints(sortedMap.get(0).getValue());
                game.setSecondPlayer(sortedMap.get(1).getKey());
                game.setSecondPlayerPoints(sortedMap.get(1).getValue());
                game.setThirdPlayer(sortedMap.get(2).getKey());
                game.setThirdPlayerPoints(sortedMap.get(2).getValue());
                gameRepository.saveGame(game);

                loggedUsers.forEach((k, v) -> {
                    try {
                        v.top(result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                this.round++;
                Random rnd = new Random();
                int c = (rnd.nextInt(categories.size()));
                currentCategory = categories.get(c);

                loggedUsers.forEach((k, v) -> {
                    try {
                        v.nextRound(current, currentCategory);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }


}
