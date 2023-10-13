package com.classroom.assignment.repository;

import java.util.List;
import com.classroom.assignment.entity.Archievement;

public interface ArchievementDao {

  List<Archievement> selectAll();

  Archievement findById(int id);

  int insertArchievement(Archievement archievement);

  int updateArchievement(Archievement archievement);

}
