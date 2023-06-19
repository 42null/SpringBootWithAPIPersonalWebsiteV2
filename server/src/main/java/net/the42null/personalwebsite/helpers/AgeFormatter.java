package net.the42null.personalwebsite.helpers;

import net.the42null.personalwebsite.Entity.GithubRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;
import java.util.regex.Pattern;

public final class AgeFormatter {

	/**
	 * Returns a string formatted to be pretty (use weeks instead of days, etc. automaticaly)
	 * @param compareDate
	 * @return
	 */

//	TODO: Compleate method
	public String getTimeSince(LocalDateTime compareDate) {
		LocalDateTime now = LocalDateTime.now();

		ChronoUnit[] units = { ChronoUnit.YEARS,
							   ChronoUnit.MONTHS,
							   ChronoUnit.DAYS,
							   ChronoUnit.HOURS,
							   ChronoUnit.MINUTES,
							   ChronoUnit.SECONDS
							};
		Function<ChronoUnit, Long> calculateDifference = unit -> unit.between(compareDate, now);
		for (ChronoUnit unit : units) {
			long difference = calculateDifference.apply(unit);
			System.out.println("unit = "+unit);
			System.out.println("difference = "+difference);
			if (difference > 0) {
				byte fancyUnitLength;
				if(difference == 1){
					fancyUnitLength = (byte) (unit.name().length()-1);
				}else{
					fancyUnitLength = (byte) unit.name().length();
				}
//				return "⌊"+difference+"⌋" + unit.name().charAt(0)+unit.name().substring(1,fancyUnitLength).toLowerCase();
				return "⌊"+difference+"⌋" + unit.name().substring(0,fancyUnitLength).toLowerCase();
			}
		}
		return "Error getting difference";
	}

	public long getFullDaysSince(LocalDateTime compareDate) {
		LocalDateTime now = LocalDateTime.now();
		return ChronoUnit.DAYS.between(compareDate, now);
	}

	public String daysToClassColor(Long days){
		if(days >= 182){//Older than 6 months
			return "date_old";
		}else if(days > 30){//Older than 1 month
			return "date_older";
		}else{//Under 1 month
			return "date_recent";
		}
	}

}
