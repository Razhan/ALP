package com.alp.mvp.games.ui;

import com.alp.mvp.games.data.model.Penalty;
import com.alp.mvp.games.data.model.Score;

public interface IAddOperationCallBack {

    void addScore(Score score);

    void addPenalty(Penalty penalty);

    void backToDetailPage();

}
