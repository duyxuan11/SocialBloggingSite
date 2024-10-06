package org.example.socialbloggingsite.favorites;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.favorites.services.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/favorites")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FavoriteController {
    FavoriteService favoriteService;

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getFavorites(@PathVariable("articleId") Integer articleId) {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        return ResponseEntity.ok(favoriteService.getFavorite(articleId));
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<?> addFavorite(@PathVariable("articleId") Integer articleId) {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        return ResponseEntity.ok(favoriteService.addFavorite(articleId));
    }
    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> unFavorite(@PathVariable("articleId") Integer articleId) {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        if(favoriteService.unFavorite(articleId)) {
            return ResponseEntity.ok().body("Favorite deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite Not Found");
    }

    @GetMapping("/getAll/{articleId}")
    public ResponseEntity<?> getListFavorites(@PathVariable("articleId") Integer articleId) {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        return ResponseEntity.ok(favoriteService.getFavoriteList(articleId));
    }
}
