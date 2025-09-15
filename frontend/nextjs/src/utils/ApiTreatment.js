export function treatDate (user) {
    // Pega a data de nascimento do usuário (no html é YYYY-MM-DD)
    let dateString = user.dataNascimento;

    let [year, month, day] = dateString.split('-');

    // Rearranja para DD/MM/YYYY
    dateString = `${day}-${month}-${year}`;

    // Substitui as barras por hífen para padronizar com o backend
    user.dataNascimento = dateString;

    // Não vou usar o Intl por enquanto por que ele está dando erro de formatação, sempre diminuindo os dias em 1
    
    // const formatter = new Intl.DateTimeFormat('pt-BR', {
    //     day: "2-digit" ,
    //     month: "2-digit",
    //     year:"numeric"
    // })
    // .format(new Date(dateString));
}

