package com.example.demo.live.repositories;

import com.example.demo.live.entity.SongLive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongLiveRepository extends JpaRepository<SongLive, String> {
}
