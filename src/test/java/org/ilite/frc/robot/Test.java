package org.ilite.frc.robot;

import org.ilite.frc.robot.modules.Shooter;
import org.ilite.frc.robot.sensors.PressureSensor;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class Test {

    @Mock
    Shooter shooter;

    //shootTest
    @org.junit.Test
    public void testShoot() {
        when(shooter.shoot()).thenReturn(true);
        assertTrue(shooter.shoot());
    }
    
    //dumpTest
    @org.junit.Test
    public void testDump() {
    	when(shooter.dump()).thenReturn(true);
    	assertTrue(shooter.dump());
    }
    

}
