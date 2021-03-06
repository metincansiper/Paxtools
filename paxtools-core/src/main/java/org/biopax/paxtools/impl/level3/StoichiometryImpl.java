package org.biopax.paxtools.impl.level3;

import org.biopax.paxtools.model.BioPAXElement;
import org.biopax.paxtools.model.level3.PhysicalEntity;
import org.biopax.paxtools.model.level3.Stoichiometry;


public class StoichiometryImpl extends L3ElementImpl implements Stoichiometry
{
	private float stoichiometricCoefficient = UNKNOWN_FLOAT;
	private PhysicalEntity physicalEntity;


	public StoichiometryImpl() {}

	public Class<? extends Stoichiometry> getModelInterface()
	{
		return Stoichiometry.class;
	}

	@Override
	protected boolean semanticallyEquivalent(BioPAXElement element)
	{

		boolean value = false;
		if (element instanceof Stoichiometry)
		{
			Stoichiometry that = (Stoichiometry) element;
			if (that.getPhysicalEntity() != null && this.getPhysicalEntity() != null)
			{
				value = (that.getStoichiometricCoefficient() ==
				         this.getStoichiometricCoefficient()) &&
				        that.getPhysicalEntity().equals(this.getPhysicalEntity());

			}
		}
		return value;
	}

	@Override
	public int equivalenceCode()
	{
		return ((int) this.getStoichiometricCoefficient()) +
	       ((this.getPhysicalEntity() != null)
	        ? 31 * this.getPhysicalEntity().hashCode()
	        : 0);
	}

	public PhysicalEntity getPhysicalEntity()
	{
		return physicalEntity;
	}

	public void setPhysicalEntity(PhysicalEntity PhysicalEntity)
	{
		this.physicalEntity = PhysicalEntity;
	}

	
	public float getStoichiometricCoefficient()
	{
		return stoichiometricCoefficient;
	}

	public void setStoichiometricCoefficient(float newStoichiometricCoefficient)
	{
		stoichiometricCoefficient = newStoichiometricCoefficient;
	}
}
