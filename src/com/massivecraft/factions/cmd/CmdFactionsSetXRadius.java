package com.massivecraft.factions.cmd;

import com.massivecraft.factions.cmd.arg.ARFaction;
import com.massivecraft.factions.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;


public abstract class CmdFactionsSetXRadius extends CmdFactionsSetX
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsSetXRadius(boolean claim)
	{
		// Super
		super(claim);
		
		// Args
		this.addArg(ARInteger.get(), "radius", "1");
		if (claim)
		{
			this.addArg(ARFaction.get(), "faction", "you");
			this.setFactionArgIndex(1);
		}
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public Integer getRadius() throws MassiveException
	{
		int radius = this.readArg(1);
		
		// Radius Claim Min
		if (radius < 1)
		{
			msg("<b>If you specify a radius, it must be at least 1.");
			return null;
		}
		
		// Radius Claim Max
		if (radius > MConf.get().setRadiusMax && ! msender.isUsingAdminMode())
		{
			msg("<b>The maximum radius allowed is <h>%s<b>.", MConf.get().setRadiusMax);
			return null;
		}
		
		return radius;
	}
	
	public Integer getRadiusZero() throws MassiveException
	{
		Integer ret = this.getRadius();
		if (ret == null) return ret;
		return ret - 1;
	}
	
}
