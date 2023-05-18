package ca.qc.colval.projet1.dao;

import java.util.List;


import ca.qc.colval.projet1.entities.Contract;
import ca.qc.colval.projet1.entities.Contract;

public interface IConventionDAO {

    //List<Convention> getAllConventions();
    Contract getContractbyId(int id);
    //Convention addConvention(Convention convention);
    //Convention updateConventionbyId(int id, Convention convention);
    //Convention deleteConventionbyId(int id);
}
