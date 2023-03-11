package ua.edu.ukma.mandarin.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.dto.ScheduleDTO;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;
import ua.edu.ukma.mandarin.scheduler.repository.SchedulerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository schedulerRepository;


    public List<ScheduleDTO> findAll() {
        return schedulerRepository.findAll()
                .stream()
                .map(x -> new ScheduleDTO(x.getId(), x.getGroup().getId(), x.getDay().toString(),
                        x.getStartTime().toString(), x.getEndTime().toString()))
                .collect(Collectors.toList());
    }

    public List<ScheduleDTO> findByStartDate(long startTime) {
        return schedulerRepository.findAllByStartTime(startTime)
                .stream()
                .map(x -> new ScheduleDTO(x.getId(), x.getGroupId(), x.getDay().toString(),
                        x.getStartTime().toString(), x.getEndTime().toString()))
                .collect(Collectors.toList());
    }

    public Schedule findById(long id) {
        return schedulerRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        schedulerRepository.deleteById(id);
    }


}
