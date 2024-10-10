package org.example.socialbloggingsite.favorites;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.favorites.services.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/favorites")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FavoriteController {
    FavoriteService favoriteService;

    @GetMapping(value = {"/","/{articleId}"})
    public ResponseEntity<?> getFavorites(@PathVariable("articleId") Optional<Integer> articleId) {
        if(articleId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        return ResponseEntity.ok(favoriteService.getFavorite(articleId.get()));
    }

    @PostMapping(value = {"/","/{articleId}"})
    public ResponseEntity<?> addFavorite(@PathVariable("articleId") Optional<Integer> articleId) {
        if(articleId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        return ResponseEntity.ok(favoriteService.addFavorite(articleId.get()));
    }
    @DeleteMapping(value = {"/","/{articleId}"})
    public ResponseEntity<?> unFavorite(@PathVariable("articleId") Optional<Integer> articleId) {
        if(articleId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        if(favoriteService.unFavorite(articleId.get())) {
            return ResponseEntity.ok().body("Favorite deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite Not Found");
    }

    @GetMapping(value = {"/getAll/","/getAll/{articleId}"})
    public ResponseEntity<?> getListFavorites(@PathVariable("articleId") Optional<Integer> articleId) {
        if(articleId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        return ResponseEntity.ok(favoriteService.getFavoriteList(articleId.get()));
    }
}
