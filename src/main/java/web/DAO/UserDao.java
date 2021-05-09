package web.DAO;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getListUser();
    User getById(long id);
    void add(User user);
    void delete(User user);
    void edit(User user);
}
