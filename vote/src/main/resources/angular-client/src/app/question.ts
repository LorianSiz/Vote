export enum TYPE {
    MULTICHOIX,
    SIMPLECHOIX,
    JAUGE
}

export class Question {
    id: string;
    contenu: string;
    type: TYPE;
    reponses: string[];
    formulaireId: string;
}