package ar.edu.unq.desapp.grupoa;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class ArchitectureTest {

    @Test
    public void testLayersConnections() {
        List<Class<?>> webservicePackage = ClassFinder.find("ar.edu.unq.desapp.grupoa.webservice");
        assertTrue(webservicePackage.stream().allMatch(this::checkIfHasCorrectLayers));
    }

    private boolean checkIfHasCorrectLayers(Class clazz) {
        String name = clazz.getSimpleName().split("Controller")[0].toLowerCase();
        String serviceName = name + "Service";
        String repositoryName = name + "Repository";
        Field fieldService = ReflectionUtils.findField(clazz, serviceName);
        Class serviceClass = fieldService.getType();
        Field fieldRepository = ReflectionUtils.findField(serviceClass, repositoryName);
        Class repositoryClass = fieldRepository.getType();
        return Objects.nonNull(serviceClass) && Objects.nonNull(repositoryClass);
    }

}
