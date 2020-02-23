package ar.edu.unq.desapp.grupoa;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.junit.Assert.assertTrue;

public class ArchitectureTest {

    @Test
    public void testLayersConnections() {
        List<Class<?>> webservicePackage = ClassFinder.find("ar.edu.unq.desapp.grupoa.webservice");
        assertTrue(webservicePackage.stream().allMatch(this::checkIfHasCorrectLayers));
    }

    private boolean checkIfHasCorrectLayers(Class<?> clazz) {
        String name = clazz.getSimpleName().split("Controller")[0].toLowerCase();
        String serviceName = name + "Service";
        String repositoryName = name + "Repository";
        boolean serviceFound = false;
        boolean repositoryFound = false;
        Field fieldService = ReflectionUtils.findField(clazz, serviceName);
        serviceFound = !isNull(fieldService);
        if (serviceFound) {
            Class<?> serviceClass = fieldService.getType();
            Field fieldRepository = ReflectionUtils.findField(serviceClass, repositoryName);
            repositoryFound = !isNull(fieldRepository);
        }
        return serviceFound && repositoryFound;
    }

}
