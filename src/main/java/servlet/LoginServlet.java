package servlet;

import dao.PersonDao;
import pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/homePage")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PersonDao personDao = new PersonDao();
       // personDao.registerNewPerson(new Person());

        Person person = personDao.checkAndReturnPersonByLoginAndPassword(req.getParameter("login"), req.getParameter("password"));
        if (person != null) {
            session.setAttribute("currentUser", person);
            getServletContext().getRequestDispatcher("/mainPage.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/loginPageAfterWrongParameters.jsp").forward(req, resp);
        }
    }
}
