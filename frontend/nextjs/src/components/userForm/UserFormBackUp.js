import { useState } from "react";
import Input from "../layout/form/Input"
import SubmitButton from "../layout/form/SubmitButton"
import { treatDate } from "../../utils/ApiTreatment";
import { useForm } from "react-hook-form";

export default function UserFormBackUp ({text}) {
  const form = useForm({
    resolver: zodResolver(newUserCreationSchema)
  });
  const [user , setUser] = useState({});
  
  function handleOnChange(e) {
    setUser({ ...user, [e.target.name]: e.target.value });
  }

  const submit = (e) => {
    e.preventDefault();
    treatDate(user)
    user.atividades = [];
    fetch("http://localhost:8080/alunos" , {
      method : "POST",
      headers : {
        "Content-Type" : "application/json"
      },
      body : JSON.stringify(user)
    })
    .then((resp) => resp.json())
    .then((data) => {
      console.log(data)
    })
    .catch((err) => console.log(err))
  };

  return (
    <form onSubmit={submit}>
      <Input type="text" text="Name" name="name" placeholder="Digite seu nome completo" handleOnChange={handleOnChange} value={user.name ? user.name : ''} />

      <Input type="email" name="email" text="Email"  placeholder="Digite seu endereço de email" handleOnChange={handleOnChange} value={user.email ? user.email : ''} />

      <Input type="date" name="dataNascimento" text="Data de Nascimento"  placeholder="" handleOnChange={handleOnChange} value={user.dataNascimento ? user.dataNascimento : ''} />

      <Input type="text" name="curso" text="Curso"  placeholder="Digite seu curso" handleOnChange={handleOnChange} value={user.curso ? user.curso : ''} />
      <Input type="text" name="classe" text="Classe"  placeholder="Digite sua classe " handleOnChange={handleOnChange} value={user.classe ? user.classe : ''} />

      <SubmitButton text={text}/>
    </form >
  )
}