import { z } from "zod";

// Regex simples para validar formato YYYY-MM-DD (sem lógica de calendário)
const dateRegex = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;

export const newUserCreationSchema = z.object({
  name: z.string().min(3, { message: "O nome deve ter no mínimo 3 caracteres." }),
  email: z.string().email({ message: "Insira um email válido." }),
  dataNascimento: z
    .string()
    .regex(dateRegex, { message: "Campo obrigatório" }),
  curso: z.string().min(2, { message: "O curso deve ter no mínimo 2 caracteres." }),
  classe: z.string().min(1, { message: "A classe é obrigatória." }),
});

export type NewUserCreation = z.infer<typeof newUserCreationSchema>;
