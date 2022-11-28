package com.souvenirstore.web;

import com.google.gson.Gson;
import com.souvenirstore.bean.User;
import com.souvenirstore.service.UserService;
import com.souvenirstore.service.impl.UserServiceImpl;
import com.souvenirstore.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * check whether the username exists in the database
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        boolean existsUsername = userService.existsUsername(username);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * Log Out
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 invalid session
        req.getSession().invalidate();
        // 2 redirect to home page
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * Log In
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.loginUser(new User(null, username, password, null));

        if (loginUser == null) {

            req.setAttribute("msg", "The username or password is wrong.");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);

        } else {

            req.getSession().setAttribute("user", loginUser);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }

    /**
     * Register
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get the code in the Session
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // remove the code in the Session
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // check whether the code is right
        if (token!=null && token.equalsIgnoreCase(code)) {
            // check whether the username is new
            if (userService.existsUsername(username)) {

                req.setAttribute("msg", "This username already exists.");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            } else {

                userService.registerUser(new User(null, username, password, email));
                req.getSession().setAttribute("user", user);

                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        } else {

            req.setAttribute("msg", "The code is wrong.");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }
}
