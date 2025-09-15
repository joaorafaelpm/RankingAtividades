import Input from "../layout/form/Input";
import SubmitButton from "../layout/form/SubmitButton";
import { treatDate } from "../../utils/ApiTreatment";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { newUserCreationSchema, NewUserCreation } from "../../utils/UserValidators";
import { useState } from "react";

import style from "./UserForm.module.css"


interface UserFormProps {
  text: string;
}

export default function UserForm({ text }: UserFormProps) {
  const [message, setMessage] = useState("");

  // Usando a biblioteca do zod para formulários com react-hook-form
  // Isso é um cu de programar, eu espero receber várias recompensas carnais por isso
  const {
    // Diferente de antes, eu posso usar essa função register para registrar a informação do input assim que ele receber o submit
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<NewUserCreation>({
    resolver: zodResolver(newUserCreationSchema),
  });

  // Função chamada pelo handleSubmit
  const submit = async (data: NewUserCreation) => {
    const user = { ...data, atividades: [] };
    treatDate(user);

    try {
      const resp = await fetch("http://localhost:8080/alunos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
      });

      const result = await resp.json();
      result.title ? setMessage(result.title)  : setMessage("Usuário criado com sucesso!");
      console.log("Usuário criado com sucesso:", result);
      reset();
    } catch (err) {
      console.error("Erro ao criar usuário:", err);
    }
  };

  return (
    <form
      onSubmit={handleSubmit(submit)}
      className="flex flex-col gap-4 max-w-md"
    >
      {/* Nome */}
      <div>
        <Input placeholder="Digite seu nome completo" {...register("name")} />
        {errors.name && (
          <p className="text-red-600 text-sm">{errors.name.message}</p>
        )}
      </div>

      {/* Email */}
      <div>
        <Input
          placeholder="Digite seu endereço de email"
          {...register("email")}
        />
        {errors.email && (
          <p className="text-red-600 text-sm">{errors.email.message}</p>
        )}
      </div>

      {/* Data de nascimento */}
      <div>
        <Input type="date" {...register("dataNascimento")} />
        {errors.dataNascimento && (
          <p className="text-red-600 text-sm">
            {errors.dataNascimento.message}
          </p>
        )}
      </div>

      {/* Curso */}
      <div>
        <Input placeholder="Digite seu curso" {...register("curso")} />
        {errors.curso && (
          <p className="text-red-600 text-sm">{errors.curso.message}</p>
        )}
      </div>

      {/* Classe */}
      <div>
        <Input placeholder="Digite sua classe" {...register("classe")} />
        {errors.classe && (
          <p className="text-red-600 text-sm">{errors.classe.message}</p>
        )}
      </div>

      {message && <span className="text-green-600 text-sm">{message}</span>}

      <SubmitButton text={text} />
    </form>
  );
}
