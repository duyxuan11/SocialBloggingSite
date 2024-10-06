package org.example.socialbloggingsite.favorites.services;

import org.example.socialbloggingsite.favorites.dto.FavoriteListResponse;
import org.example.socialbloggingsite.favorites.dto.FavoriteResponse;

public interface FavoriteService {
    FavoriteResponse getFavorite(Integer articleId);
    FavoriteResponse addFavorite(Integer articleId);
    Boolean unFavorite(Integer articleId);
    FavoriteListResponse getFavoriteList(Integer articleId);
}
