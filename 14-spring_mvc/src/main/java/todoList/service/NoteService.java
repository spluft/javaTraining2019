package todoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoList.model.Note;
import todoList.model.User;
import todoList.service.exception.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    Connection connection;

    public void add(Note note) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into notes (login, message) values (? ,?)");
            preparedStatement.setObject(1, note.getLogin());
            preparedStatement.setObject(2, note.getMessage());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    public List<Note> getAll(User user) {
        List<Note> noteList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from notes where login = ?");
            preparedStatement.setObject(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setId(resultSet.getInt(1));
                note.setMessage(resultSet.getString(3));
                noteList.add(note);
            }

        } catch (SQLException e) {
            throw new DataBaseException();
        }

        return noteList;
    }

    public void delete(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from notes where id = ?");
            preparedStatement.setObject(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

    public void delete(String login, boolean all) {
        PreparedStatement preparedStatement = null;
        try {

            if (all) {
                preparedStatement = connection.prepareStatement("delete from notes where login = ?");
                preparedStatement.setObject(1, login);
                preparedStatement.execute();
            } else {

                preparedStatement = connection.prepareStatement("select min(id) from notes where login = ?");
                preparedStatement.setObject(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int first = resultSet.getInt(1);
                    preparedStatement = connection.prepareStatement("delete from notes where id = ?");
                    preparedStatement.setObject(1, first);
                    preparedStatement.execute();
                }

            }

        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }

}
