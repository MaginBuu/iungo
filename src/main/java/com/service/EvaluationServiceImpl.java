package com.service;

import com.dao.EvaluationDao;
import com.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EvaluationDao evaluationDao;

    public void addEvaluation(Evaluation evaluation){
        evaluationDao.addEvaluation(evaluation);
    }
}
