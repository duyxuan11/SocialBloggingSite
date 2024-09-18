package org.example.socialbloggingsite.user.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtTokenBlackListService {
    private Set<String> blacklist = new HashSet<String>();

    public void addBlacklist(String token) {
        blacklist.add(token);
    }

    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
