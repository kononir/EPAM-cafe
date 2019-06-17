package com.epam.cafe.util;

import com.epam.cafe.api.util.BonusHelper;
import com.epam.cafe.entitie.Bonus;

import java.util.List;
import java.util.Optional;

public class BonusHelperImpl implements BonusHelper {

    @Override
    public Bonus findBonusById(List<Bonus> bonuses, int bonusID) {
        Optional<Bonus> foundBonus = bonuses.stream()
                .filter(bonus -> (bonus.getID() == bonusID))
                .findFirst();

        return foundBonus.orElse(null);
    }
}
