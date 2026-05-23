import { GameStateDTO } from "./GameStateDTO";
import { IncidentDTO } from "./IncidentDTO";

export interface VarRequestDTO{
    gameState: GameStateDTO,
    incident: IncidentDTO
}