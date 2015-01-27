package mods.eln.item;

import mods.eln.generic.GenericItemUsingDamageDescriptor;
import mods.eln.node.IThermalDestructorDescriptor;
import mods.eln.sim.ThermalLoad;
import mods.eln.sim.ThermalResistor;


public class ThermalIsolatorElement extends GenericItemUsingDamageDescriptorUpgrade {
	

	
	public double conductionFactor,Tmax;

	public ThermalIsolatorElement(String name,
								  double isolationFactor,
								  double Tmax
								//double thermalNominalT,double thermalNominalP
								) {
		super(name);
		
		this.Tmax = Tmax;
		this.conductionFactor = isolationFactor;

	}
	
/*
	public void applyTo(ThermalLoad resistor)
	{
		resistor.setRp(thermalRp);
	}*/
}