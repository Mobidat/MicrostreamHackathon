package com.zillus.coronadiary.dal;

import com.zillus.coronadiary.domain.SymptomEntity;
import com.zillus.coronadiary.microstream.DB;
import org.junit.jupiter.api.*;

class TreatmentDAOTest {

    @BeforeEach
    void clearStore() {
        DB.root().getTreatmentEntities().clear();
        DB.storageManager().store(DB.root().getTreatmentEntities());
    }

    @AfterAll
    static void shutdown() {
        DB.shutDown();
    }

    @Test
    void isSavedReflectsPresence() {
        SymptomEntity symptom = new SymptomEntity();
        Assertions.assertFalse(TreatmentDAO.isSaved(symptom));

        TreatmentDAO.addEntity(symptom);
        Assertions.assertTrue(TreatmentDAO.isSaved(symptom));

        TreatmentDAO.removeEntity(symptom);
        Assertions.assertFalse(TreatmentDAO.isSaved(symptom));
    }

    @Test
    void isSavedNullReturnsFalse() {
        Assertions.assertFalse(TreatmentDAO.isSaved(null));
    }

    @Test
    void addAndRemoveUpdatesCollection() {
        SymptomEntity symptom = new SymptomEntity();
        TreatmentDAO.addEntity(symptom);
        Assertions.assertTrue(TreatmentDAO.findAll().contains(symptom));

        TreatmentDAO.removeEntity(symptom);
        Assertions.assertFalse(TreatmentDAO.findAll().contains(symptom));
    }
}
