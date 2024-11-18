package com.example.demo.repositories.backup;

import com.example.demo.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongBackupRepository extends JpaRepository<Song, String> {
}
