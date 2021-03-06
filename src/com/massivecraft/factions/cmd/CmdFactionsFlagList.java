package com.massivecraft.factions.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.factions.Perm;
import com.massivecraft.factions.entity.MFlag;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

public class CmdFactionsFlagList extends FactionsCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdFactionsFlagList()
	{
		// Aliases
		this.addAliases("l", "list");
		
		// Args
		this.addArg(ARInteger.get(), "page", "1");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.FLAG_LIST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int pageHumanBased = this.readArg(1);
		
		//Create messages
		List<String> messages = new ArrayList<String>();
		
		for (MFlag flag : MFlag.getAll())
		{
			if ( ! flag.isVisible() && ! msender.isUsingAdminMode()) continue;
			messages.add(flag.getStateDesc(false, false, true, true, true, false));
		}
		
		//Send messages
		sendMessage(Txt.getPage(messages, pageHumanBased, "Available Faction Flags", sender));
	}
	
}
