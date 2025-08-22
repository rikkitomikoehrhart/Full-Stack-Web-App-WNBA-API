export function generatePlayerHeadshotPath(player) {
    const sanitizeName = (name) => {
        return name
            .toLowerCase()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "")
            .replace(/[^a-z0-9]/g, "");
    };

    const first_name = sanitizeName(player.first_name);
    const last_name = sanitizeName(player.last_name);

    const path = "/player-headshots/";

    return path + first_name + "_" + last_name + ".png";
}

export function getFormattedDate(dateString) {
    const date = new Date(dateString);
    const month = date.toLocaleDateString('default', { month: "short" });

    return month + " " + date.getDate() + ", " + date.getFullYear();
}

export function getFormattedDateTime(dateString) {
    const date = new Date(dateString);
    const month = date.toLocaleDateString('default', { month: "short" });
    const time = date.toLocaleString('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true
    });

    return month + " " + date.getDate() + ", " + date.getFullYear() + " at " + time;
}

export function getFormattedHeight(height) {
    const feet = Math.floor(height/12);
    const inches = height%12;

    return feet + `" ` + inches + `'`;
}

export function getFormattedAge(birthdateString) {
    const birthdate = new Date(birthdateString);
    const today = new Date();

    let age = today.getFullYear() - birthdate.getFullYear();
    const monthDifference = today.getMonth() - birthdate.getMonth();
    const dayDifference = today.getDate() - birthdate.getDate();

    if (monthDifference < 0 || (monthDifference === 0 && dayDifference <0)) {
        age--;
    }

    return age;
}

export function getZodiacSign(birthdateString) {
    const birthdate = new Date(birthdateString);

    if (birthdate.getMonth() == 0) {
        if (birthdate.getDate() <= 19) {
            return "Capricorn";
        } else {
            return "Aquarius";
        }
    } else if (birthdate.getMonth() == 1) {
        if (birthdate.getDate() <= 18) {
            return "Aquarius";
        } else {
            return "Pisces";
        }
    } else if (birthdate.getMonth() == 2) {
        if (birthdate.getDate() <= 20) {
            return "Pisces";
        } else {
            return "Aries";
        }
    } else if (birthdate.getMonth() == 3) {
        if (birthdate.getDate() <= 19) {
            return "Aries";
        } else {
            return "Taurus";
        }
    } else if (birthdate.getMonth() == 4) {
        if (birthdate.getDate() <= 20) {
            return "Taurus";
        } else {
            return "Gemini";
        }
    } else if (birthdate.getMonth() == 5) {
        if (birthdate.getDate() <= 21) {
            return "Gemini";
        } else {
            return "Cancer";
        }
    } else if (birthdate.getMonth() == 6) {
        if (birthdate.getDate() <= 22) {
            return "Cancer";
        } else {
            return "Leo";
        }
    } else if (birthdate.getMonth() == 7) {
        if (birthdate.getDate() <= 22) {
            return "Leo";
        } else {
            return "Virgo";
        }
    } else if (birthdate.getMonth() == 8) {
        if (birthdate.getDate() <= 22) {
            return "Virgo";
        } else {
            return "Libra";
        }
    } else if (birthdate.getMonth() == 9) {
        if (birthdate.getDate() <= 23) {
            return "Libra";
        } else {
            return "Scorpio";
        }
    } else if (birthdate.getMonth() == 10) {
        if (birthdate.getDate() <= 21) {
            return "Scorpio";
        } else {
            return "Sagittarius";
        }
    } else if (birthdate.getMonth() == 11) {
        if (birthdate.getDate() <= 21) {
            return "Sagittarius";
        } else {
            return "Capricorn";
        }
    }
}
