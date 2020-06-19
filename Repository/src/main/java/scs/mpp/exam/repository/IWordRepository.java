package scs.mpp.exam.repository;

import scs.mpp.exam.model.Word;

import java.util.List;

public interface IWordRepository extends Repository {
    List<Word> getWords();
}
