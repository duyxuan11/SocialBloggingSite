package org.example.socialbloggingsite.favorites.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.articles.ArticleRepository;
import org.example.socialbloggingsite.articles.dto.ArticleResponse;
import org.example.socialbloggingsite.articles.dto.UserArticleResponse;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.favorites.FavoriteEntity;
import org.example.socialbloggingsite.favorites.dto.FavoriteListResponse;
import org.example.socialbloggingsite.favorites.FavoriteRepository;
import org.example.socialbloggingsite.favorites.dto.FavoriteResponse;
import org.example.socialbloggingsite.favorites.services.FavoriteService;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteServiceImpl implements FavoriteService {

    ArticleRepository articleRepository;
    FavoriteRepository favoriteRepository;
    ModelMapper modelMapper;
    UserRepository userRepository;

    @Override
    @Transactional
    public FavoriteResponse getFavorite(Integer articleId){
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getName());
        var favorites = favoriteRepository.findByArticleAndUser(article,user.get());
        if (favorites.isEmpty()){
            return new FavoriteResponse();
        }
        var userrespone = userRepository.findByUsername(favorites.get().getUser().getUsername());
        var favoriteResponse = modelMapper.map(favorites, FavoriteResponse.class);
        favoriteResponse.setUser(modelMapper.map(userrespone, UserArticleResponse.class));
        List<FavoriteEntity> userEntities;
        userEntities = favoriteRepository.findByArticleId(articleId);
        userEntities.stream().forEach(userEntity -> {
            log.info(userEntity.getUser().getUsername());
        });
        return favoriteResponse;
    }

    @Override
    @Transactional
    public FavoriteResponse addFavorite(Integer articleId){
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getName());
        FavoriteEntity favorite = new FavoriteEntity();
        favorite.setArticle(article);
        favorite.setUser(user.get());
        favoriteRepository.save(favorite);
        return modelMapper.map(favorite, FavoriteResponse.class);
    }

    @Override
    @Transactional
    public Boolean unFavorite(Integer articleId){
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getName());
        var favorites = favoriteRepository.findByArticleAndUser(article,user.get());
        if (favorites.isEmpty()){
            return false;
        }
        favoriteRepository.delete(favorites.get());
        return true;

    }

    @Override
    public FavoriteListResponse getFavoriteList(Integer articleId){
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        List<FavoriteEntity> userEntities;
        List<UserArticleResponse> userArticleResponses = new ArrayList<>();
        userEntities = favoriteRepository.findByArticleId(articleId);
        userEntities.stream().forEach(userEntity -> {
            log.info(userEntity.getUser().getUsername());
            var userArticleResponse = modelMapper.map(userEntity.getUser(), UserArticleResponse.class);
            userArticleResponses.add(userArticleResponse);
        });
        FavoriteListResponse favoriteListResponse = FavoriteListResponse.builder().article(modelMapper.map(article,ArticleResponse.class))
                .user(userArticleResponses).build();
        return favoriteListResponse;
    }

}
