package ca.qc.colval.projet1.dao;


import java.util.List;

import ca.qc.colval.projet1.entities.Check;

public interface ICheckDAO {

    List<Check> getAllChecks();

    Check getCheckbyId(int id);

    Check addCheck(Check check);

    Check updateCheckbyId(int id, Check check);

    Check deleteCheckbyId(int id);
}
