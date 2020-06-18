package scs.mpp.exam.services;

public interface Services {
    void test();
    void login(String user, String password, Observer observer) throws Exception;
}
