package com.example.demo.service;

import com.example.demo.common.request.SongRequest;
import com.example.demo.entity.Song;
import com.example.demo.repositories.backup.SongBackupRepository;
import com.example.demo.repositories.live.SongLiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongLiveRepository songLiveRepository;

    private final SongBackupRepository songBackupRepository;

    public Song save(SongRequest request) {
        String id = UUID.randomUUID().toString();
        Song songLive = Song.builder()
                                .id(id)
                                .name(request.getName())
                                .duration(request.getDuration())
                                .build();
        songLiveRepository.save(songLive);
        songBackupRepository.save(songLive);
        return songLive;
    }

    public Song update(String id, SongRequest request) {
        Optional<Song> songLive = songLiveRepository.findById(id);
        songLive.get().setName(request.getName());
        songLive.get().setDuration(request.getDuration());
        Optional<Song> songBackup = songBackupRepository.findById(id);
        songBackup.get().setName(request.getName());
        songBackup.get().setDuration(request.getDuration());
        songLiveRepository.save(songLive.get());
        songBackupRepository.save(songBackup.get());
        return songLive.get();
    }

    public Song delete(String id) {
        Optional<Song> songLive = songLiveRepository.findById(id);
        Optional<Song> songBackup = songBackupRepository.findById(id);
        songLiveRepository.delete(songLive.get());
        songBackupRepository.delete(songBackup.get());
        return songLive.get();
    }

    public Song search(String id) {
        try {
            Optional<Song> object = songLiveRepository.findById(id);
            return object.get();
        } catch (Exception e) {
            Optional<Song> object = songBackupRepository.findById(id);
            return object.get();
        }
    }

}
