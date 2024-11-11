package com.example.demo.service;

import com.example.demo.backup.entity.SongBackup;
import com.example.demo.backup.repositories.SongBackupRepository;
import com.example.demo.common.request.SongRequest;
import com.example.demo.live.entity.SongLive;
import com.example.demo.live.repositories.SongLiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongLiveRepository songLiveRepository;

    private final SongBackupRepository songBackupRepository;

    public SongLive save(SongRequest request) {
        String id = UUID.randomUUID().toString();
        SongLive songLive = SongLive.builder()
                                .id(id)
                                .name(request.getName())
                                .duration(request.getDuration())
                                .build();
        SongBackup songBackup = SongBackup.builder()
                .id(id)
                .name(request.getName())
                .duration(request.getDuration())
                .build();
        songLiveRepository.save(songLive);
        songBackupRepository.save(songBackup);
        return songLive;
    }

    public SongLive update(String id, SongRequest request) {
        Optional<SongLive> songLive = songLiveRepository.findById(id);
        songLive.get().setName(request.getName());
        songLive.get().setDuration(request.getDuration());
        Optional<SongBackup> songBackup = songBackupRepository.findById(id);
        songBackup.get().setName(request.getName());
        songBackup.get().setDuration(request.getDuration());
        songLiveRepository.save(songLive.get());
        songBackupRepository.save(songBackup.get());
        return songLive.get();
    }

    public SongLive delete(String id) {
        Optional<SongLive> songLive = songLiveRepository.findById(id);
        Optional<SongBackup> songBackup = songBackupRepository.findById(id);
        songLiveRepository.delete(songLive.get());
        songBackupRepository.delete(songBackup.get());
        return songLive.get();
    }

    public SongLive search(String id) {
        Optional<SongLive> object = songLiveRepository.findById(id);
        return object.get();
    }


}
