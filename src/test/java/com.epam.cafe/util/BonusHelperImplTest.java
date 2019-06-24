package com.epam.cafe.util;

import com.epam.cafe.api.util.BonusHelper;
import com.epam.cafe.entitie.Bonus;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BonusHelperImplTest {
    private static final int CORRECT_BONUS_ID = 2;
    private static final String CORRECT_BONUS_NAME = "name2";
    private static final String CORRECT_BONUS_DESCRIPTION = "description2";
    private static final int CORRECT_USER_ID = 2;

    private static final int INCORRECT_BONUS_ID = 100;

    private List<Bonus> bonuses = Arrays.asList(
            new Bonus(1, "name1", "description1", 1),
            new Bonus(CORRECT_BONUS_ID, CORRECT_BONUS_NAME, CORRECT_BONUS_DESCRIPTION, CORRECT_USER_ID)
    );

    private List<Bonus> emptyBonuses = Collections.emptyList();

    @Test
    public void testFindBonusByIdShouldReturnBonusWhenGivenCorrectBonusID() {
        BonusHelper helper = new BonusHelperImpl();

        Bonus bonus = helper.findBonusById(bonuses, CORRECT_BONUS_ID);

        Assert.assertEquals(CORRECT_BONUS_ID, bonus.getUserID());
        Assert.assertEquals(CORRECT_BONUS_NAME, bonus.getName());
        Assert.assertEquals(CORRECT_BONUS_DESCRIPTION, bonus.getDescription());
        Assert.assertEquals(CORRECT_USER_ID, bonus.getUserID());
    }

    @Test
    public void testFindBonusByIdShouldReturnNullWhenGivenIncorrectBonusID() {
        BonusHelper helper = new BonusHelperImpl();

        Bonus bonus = helper.findBonusById(bonuses, INCORRECT_BONUS_ID);

        Assert.assertNull(bonus);
    }

    @Test
    public void testFindBonusByIdShouldReturnNullWhenGivenEmptyBonusList() {
        BonusHelper helper = new BonusHelperImpl();

        Bonus bonus = helper.findBonusById(emptyBonuses, CORRECT_BONUS_ID);

        Assert.assertNull(bonus);
    }
}