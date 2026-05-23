export interface GameStateDTO{
    location: string,
    distanceFromGoal: string,
    movingDirection: string,
    ballControl: boolean,
    numDefendersAhead: number,
    goalScored: boolean
}