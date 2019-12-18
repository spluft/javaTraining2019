package todoList.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import todoList.model.Note;
import todoList.model.User;
import todoList.service.NoteService;
import todoList.service.UserService;
import todoList.service.exception.DataBaseException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TodoListController {

    public static final Logger log = LogManager.getLogger();

    private static String MAIN_PAGE = "index";
    private static String TODO_PAGE = "todo";
    private static String NOTES = "notes";
    private static String EMPTY = "";

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String getHomePage(Locale locale, HttpServletRequest request) {
        log.info("open login page");
        return MAIN_PAGE;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public ModelAndView checkUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpServletResponse response) throws SQLException {
        log.info("check user from input label");
        if (result.hasErrors()) {
            Map<String, String> valid = new HashMap<>();
            valid.put("loginerr", result.hasFieldErrors("login") ? result.getFieldError("login").getDefaultMessage() : EMPTY );
            valid.put("passworderr", result.hasFieldErrors("password") ? result.getFieldError("password").getDefaultMessage() : EMPTY);
            return new ModelAndView(MAIN_PAGE, "valid", valid);
        }

        response.addCookie(new Cookie("login", user.getLogin()));
        response.addCookie(new Cookie("password", user.getPassword()));
        User saveUser = userService.save(user);

        if(saveUser == null) {
            log.info("cannot create user");
            throw new DataBaseException();
        }
        log.info("create user or use existing");
        List<Note> all = noteService.getAll(saveUser);

        return new ModelAndView(TODO_PAGE, NOTES, all);
    }

    @PostMapping(path = "del")
    public ModelAndView delete(@ModelAttribute("id") String id, @CookieValue("login") String login, @CookieValue("password") String password) {
        log.info("delete todo with id = " + id);
        noteService.delete(Integer.parseInt(id));
        List<Note> all = noteService.getAll(new User(login, password));
        return new ModelAndView(TODO_PAGE, NOTES, all);
    }

    @RequestMapping(path = "del/{action}", method = RequestMethod.POST)
    public ModelAndView delete(@ModelAttribute("id") String id, @CookieValue("login") String login, @CookieValue("password") String password, @PathVariable("action") String action) {
        switch (action) {
            case "first":
                noteService.delete(login, false);
                log.info("delete first notes for " + login);
                break;
            case "all":
                noteService.delete(login, true);
                log.info("delete all notes for " + login);
                break;
        }

        List<Note> all = noteService.getAll(new User(login, password));
        return new ModelAndView(TODO_PAGE, NOTES, all);
    }

    @PostMapping(path = "add")
    public ModelAndView add(@CookieValue("login") String login, @CookieValue("password") String password, @ModelAttribute("mess") String mess) {
        log.info("add new notes for " + login);
        Note note = getNote(login, null);
        note.setMessage(mess);
        noteService.add(note);

        List<Note> all = noteService.getAll(new User(login, password));
        return new ModelAndView(TODO_PAGE, NOTES, all);
    }

    private Note getNote(String login, String password) {
        Note note = new Note();
        note.setLogin(login);
        return note;
    }

}
