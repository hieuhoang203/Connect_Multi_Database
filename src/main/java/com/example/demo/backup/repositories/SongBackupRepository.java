package com.example.demo.backup.repositories;

import com.example.demo.backup.entity.SongBackup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongBackupRepository extends JpaRepository<SongBackup, String> {
}
