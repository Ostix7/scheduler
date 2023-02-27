package ua.edu.ukma.mandarin.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Scheduler;
import ua.edu.ukma.mandarin.scheduler.domain.util.Faculty;
import ua.edu.ukma.mandarin.scheduler.repository.SchedulerRepository;

import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private SchedulerRepository schedulerRepository;

    public void createScheduler(int id,String name, Faculty faculty, short year){
        Scheduler scheduler = new Scheduler(id,name,faculty,year);
        schedulerRepository.save(scheduler);
    }

    public List<Scheduler> findAll(){
        return schedulerRepository.findAll();
    }

    public List<Scheduler> findAllByFaculty(){
       return schedulerRepository.findAllByFaculty();
    }


}
