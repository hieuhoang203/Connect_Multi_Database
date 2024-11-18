package com.example.demo.repositories.live;

import com.example.demo.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongLiveRepository extends JpaRepository<Song, String> {
}
