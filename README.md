Project's structure was modified to create command menue in telegram bots command string.
Was modified class CommandName - added method GetNames to receive list of command names.
Was created class CommandDescription with method GetDescriptions to receive list of command descriptions.
Was created class MenueList  to receive list of BotCommand.
Was created class BotConfig to inject bot's fields into bot constructor.
Into bots constructor injected field TelegaUserService.
In the bot constructor used class SetMyCommands. 
