package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public synchronized List<Post> all() {
        return new ArrayList<>(this.posts);
    }

    public synchronized Optional<Post> getById(long id) {
        return this.posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    public synchronized Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(this.posts.size() + 1);
            this.posts.add(post);
            return post;
        }
        for (int i = 0; i < this.posts.size(); i++) {
            if (this.posts.get(i).getId() == post.getId()) {
                this.posts.set(i, post);
                return post;
            }
        }
        this.posts.add(post);
        return post;
    }

    public synchronized void removeById(long id) {
        this.posts.removeIf(p -> p.getId() == id);
    }
}