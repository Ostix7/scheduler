package ua.edu.ukma.mandarin.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Schedule;
import ua.edu.ukma.mandarin.scheduler.domain.util.Faculty;
import ua.edu.ukma.mandarin.scheduler.repository.SchedulerRepository;

import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository schedulerRepository;


    public List<Schedule> findAll(){
        return schedulerRepository.findAll();
    }
    public List<Schedule>findByStartDate(long startTime){
        return schedulerRepository.findAllByStartTime(startTime);
    }
    public Schedule findById(long id){
        return schedulerRepository.findById(id).orElse(null);
    }
    public void deleteById(long id){
        schedulerRepository.deleteById(id);
    }




}
