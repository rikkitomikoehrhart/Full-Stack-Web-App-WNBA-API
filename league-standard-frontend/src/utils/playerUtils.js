import { DEFAULT_IMAGE_PATHS } from '../constants/ui';
import { sanitizeName } from './formatUtils';

export function generatePlayerHeadshotPath(player) {
    const firstName = sanitizeName(player.first_name); 
    const lastName = sanitizeName(player.last_name);
    
    return `${DEFAULT_IMAGE_PATHS.PLAYER_HEADSHOTS}/${firstName}_${lastName}.png`;
}