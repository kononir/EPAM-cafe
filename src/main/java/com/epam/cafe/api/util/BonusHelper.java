package com.epam.cafe.api.util;

import com.epam.cafe.entitie.bonus.Bonus;

import java.util.List;

public interface BonusHelper {
    Bonus findBonusById(List<Bonus> bonuses, int bonusID);
}
