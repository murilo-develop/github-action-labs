package testsupport;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTestBase {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void cleanDatabase() {
        this.entityManager
            .createNativeQuery("select * from truncate_tables()")
            .getResultList();
    }
}
