/*
 * InteractionProxy.java
 *
 * 2007.04.05 Takeshi Yoneki
 * INOH project - http://www.inoh.org
 */

package org.biopax.paxtools.proxy.level2;

import org.biopax.paxtools.model.level2.InteractionParticipant;
import org.biopax.paxtools.model.level2.interaction;
import org.biopax.paxtools.proxy.BioPAXElementProxy;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Set;

/**
 * Proxy for interaction
 */
@Entity(name="l2interaction")
@Indexed(index=BioPAXElementProxy.SEARCH_INDEX_NAME)
public class interactionProxy extends processProxy implements interaction {
	public interactionProxy() {
	}

	@Transient
	public Class getModelInterface()
	{
		return interaction.class;
	}

	Set<InteractionParticipant> proxyPARTICIPANTS = null;

	// interactin setter Beans
	// 2007.04.26 Takeshi Yoneki
	// setter
	// 2007.09.05
	@Transient
	public Set<InteractionParticipant> getPARTICIPANTS() {
		return ((interaction)object).getPARTICIPANTS();
	}

	public void setPARTICIPANTS(Set<InteractionParticipant> PARTICIPANTS) {
		((interaction)object).setPARTICIPANTS(PARTICIPANTS);
	}

//
// 2008.05.28 Takeshi Yoneki
//	@ManyToMany(cascade = {CascadeType.ALL}, targetEntity=InteractionParticipantProxy.class, fetch=FetchType.EAGER)
//	@JoinTable(name="l2interaction_participants_x")
	@Transient
	protected Set<InteractionParticipant> getPARTICIPANTS_x() {
		if (proxyPARTICIPANTS == null)
			proxyPARTICIPANTS = getPARTICIPANTS();
		return proxyPARTICIPANTS;
	}

	protected void setPARTICIPANTS_x(Set<InteractionParticipant> PARTICIPANTS) {
		call_setPARTICIPANTS_x(PARTICIPANTS);
	}

	protected void call_setPARTICIPANTS_x(Set<InteractionParticipant> PARTICIPANTS) {
		try {
			// 
			// 2008.05.26 Takeshi Yoneki
			Set<InteractionParticipant> olsIPs = getPARTICIPANTS();
			for (InteractionParticipant oip: olsIPs)
				removePARTICIPANTS(oip);
			for (InteractionParticipant ip: PARTICIPANTS)
				addPARTICIPANTS(ip);
		}
		catch (Exception e) {
			// Directly setting participants are not allowed !
			System.err.println(e.getMessage());
		}
		proxyPARTICIPANTS = PARTICIPANTS;
	}

	public void addPARTICIPANTS(InteractionParticipant aParticipant) {
		((interaction)object).addPARTICIPANTS(aParticipant);
		proxyPARTICIPANTS = null;
	}

	public void removePARTICIPANTS(InteractionParticipant aParticipant) {
		((interaction)object).removePARTICIPANTS(aParticipant);
		proxyPARTICIPANTS = null;
	}

}
