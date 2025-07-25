package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) {
        try {
            var article = service.getById(id);
            if (article == null) {
                throw new NotFoundException();
            }
            response.setContentType(APPLICATION_JSON);
            final var gson = new Gson();
            response.getWriter().print(gson.toJson(article));
        } catch (Exception e) {
            response.setContentType(APPLICATION_JSON);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().print("{ \"error\": \"Not Found\" }");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) {
        try {
            var article = service.getById(id);
            if (article == null) {
                throw new NotFoundException();
            }
            response.setContentType(APPLICATION_JSON);
            service.removeById(id);
            response.setContentType(APPLICATION_JSON);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            response.getWriter().print("{ \"message\": \"Resource removed successfully\" }");
        } catch (Exception e) {
            response.setContentType(APPLICATION_JSON);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                response.getWriter().print("{ \"error\": \"Not Found\" }");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}