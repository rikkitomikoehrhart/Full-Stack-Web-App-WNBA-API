const ZODIAC_SIGNS = [
    { sign: 'Capricorn', startMonth: 11, startDay: 22, endMonth: 0, endDay: 19 },
    { sign: 'Aquarius', startMonth: 0, startDay: 20, endMonth: 1, endDay: 18 },
    { sign: 'Pisces', startMonth: 1, startDay: 19, endMonth: 2, endDay: 20 },
    { sign: 'Aries', startMonth: 2, startDay: 21, endMonth: 3, endDay: 19 },
    { sign: 'Taurus', startMonth: 3, startDay: 20, endMonth: 4, endDay: 20 },
    { sign: 'Gemini', startMonth: 4, startDay: 21, endMonth: 5, endDay: 21 },
    { sign: 'Cancer', startMonth: 5, startDay: 22, endMonth: 6, endDay: 22 },
    { sign: 'Leo', startMonth: 6, startDay: 23, endMonth: 7, endDay: 22 },
    { sign: 'Virgo', startMonth: 7, startDay: 23, endMonth: 8, endDay: 22 },
    { sign: 'Libra', startMonth: 8, startDay: 23, endMonth: 9, endDay: 23 },
    { sign: 'Scorpio', startMonth: 9, startDay: 24, endMonth: 10, endDay: 21 },
    { sign: 'Sagittarius', startMonth: 10, startDay: 22, endMonth: 11, endDay: 21 }
];

export function getZodiacSign(birthdateString) {
    const birthdate = new Date(birthdateString);
    const month = birthdate.getMonth();
    const day = birthdate.getDate();

    return ZODIAC_SIGNS.find(zodiac => {
        if (zodiac.startMonth === zodiac.endMonth) {
            return month === zodiac.startMonth && day >= zodiac.startDay && day <= zodiac.endDay;
        } else {
            return (month === zodiac.startMonth && day >= zodiac.startDay) ||
                   (month === zodiac.endMonth && day <= zodiac.endDay);
        }
    })?.sign || 'Unknown';
}