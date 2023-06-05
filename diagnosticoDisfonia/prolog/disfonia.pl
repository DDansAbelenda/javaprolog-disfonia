%%%%%%%%%% PROCEDIMIENTOS CON LISTAS %%%%%%%%%%
miembro(X,[X|_]).
miembro(X,[_|L]):- miembro(X,L).

long([],0).
long([X|Tail],ListLength):- long(Tail,TailLength), ListLength is TailLength + 1.

% Verdadero cuando todos los elementos de la primera lista pertenecen a la segunda.
todos_miembros([],_).
todos_miembros([X|L1],L2):- miembro(X,L2),todos_miembros(L1,L2).


%%%%%%%%%% SíNTOMAS %%%%%%%%%%%
% comezantes
sintoma('Flema').
sintoma('Carraspera').
sintoma('Tos').
sintoma('Irritación faríngea').
sintoma('Cansancio vocal').
sintoma('Diplofonía').
%tardíos
sintoma('Ronquera').
sintoma('Afonías cortas y abruptas').
sintoma('Dificultad para los tonos agudos o graves').
sintoma('Sensación de cuerpo extraño').
sintoma('Dolor de garganta').
sintoma('Pseudosíntoma respiratorio').

%%%%%%%%%% SIGNOS %%%%%%%%%%
% visibles
signo('Contracción de los músculos de la cara').
signo('Hipertrofia de los músculos del cuello').
signo('Enrojecimiento de cara o cuello').
signo('Ingurgitación de los vasos del cuello').
signo('Sudoración, temblor, parpadeo').
%audibles
signo('Hipofonía (intensidad dismiunida)').
signo('Tono disminuido').
signo('Exhaustación respiratoria').
signo('Voz de banda').
signo('Espasticidad').
signo('Monotonía').
signo('Voz normal durante risa o llanto').

%%%%%%%%%% LARINGOSCOPÍA %%%%%%%%%%
prueba('Déficit de aducción').
prueba('Cambio de coloración de las cuerdas vocales').
prueba('Hematoma de cuerda vocal').
prueba('Nódulos').
prueba('Úlcera').
prueba('Quistes').
prueba('Lesiones quísticas').
prueba('Lesiones papilomatosas').
prueba('Pseudomixomatosis').
prueba('Edema de Reinke').
prueba('Leucoplasia').
prueba('Cáncer de Laringe').
prueba('Parálisis de cuerdas vocales').
prueba('Paresia de cuerdas vocales').
prueba('Hipertrofia de Bandas Ventriculares').
prueba('Membrana laríngea').
prueba('Aritenoides inflamados').
prueba('Aritenoides desplazados').


%%%%%%%%%% DIAGNÓSTICO %%%%%%%%%%
% diagnostica(Paciente,Diagnostico,Sintomas, Signos, Laringoscopia)
diagnostica(Paciente, 'Sano', [],[],[]):- !.
diagnostica(Paciente, 'Sano', [],[],Laringoscopia):-
      fumador(Paciente,1),
      long(Laringoscopia,1),
      miembro('Cambio de coloración de las cuerdas vocales',Laringoscopia),
      !.
                                                                       

diagnostica(Paciente,'Disfonía Psíquica', Sintomas, Signos, []):-
      long(Sintomas,Length1),Length1>0,
      miembro('Voz normal durante risa o llanto', Signos),
      !.
diagnostica(Paciente,'Disfonía Psíquica', Sintomas, Signos, Laringoscopia):-
      fumador(Paciente,1),
      long(Sintomas,Length1),Length1>0,
      miembro('Voz normal durante risa o llanto', Signos),
      long(Laringoscopia,1),
      miembro('Cambio de coloración de las cuerdas vocales',Laringoscopia),
      !.


diagnostica(Paciente,'Disfonía Funcional', Sintomas, Signos, Laringoscopia):-
      %long(Sintomas,Length1),Length1>0,
      findall(X,sintoma(X),List1), todos_miembros(Sintomas,List1),  % que todos pertenezcan a los registrados
      %long(Signos,Length2),Length2>0,
      findall(X,signo(X),List2),   todos_miembros(Signos,List2),    % que todos pertenezcan a los registrados
      long(Laringoscopia,1),
      miembro('Déficit de aducción',Laringoscopia),
      !.
diagnostica(Paciente,'Disfonía Funcional', Sintomas, Signos, Laringoscopia):-
      fumador(Paciente,1),
      %long(Sintomas,Length1),Length1>0,
      findall(X,sintoma(X),List1), todos_miembros(Sintomas,List1),  % que todos pertenezcan a los registrados
      %long(Signos,Length2),  Length2>0,
      findall(X,signo(X),List2),   todos_miembros(Signos,List2),    % que todos pertenezcan a los registrados
      long(Laringoscopia,2),
      miembro('Déficit de aducción',Laringoscopia),
      miembro('Cambio de coloración de las cuerdas vocales',Laringoscopia),
      !.


diagnostica(Paciente,'Disfonía Orgánica', Sintomas, Signos, Laringoscopia):-
      fumador(Paciente,0),
      %long(Sintomas,Length1),Length1>0,
      findall(X,sintoma(X),List1), todos_miembros(Sintomas,List1),  % que todos pertenezcan a los registrados
      %long(Signos,Length2),  Length2>0,
      findall(X,signo(X),List2),   todos_miembros(Signos,List2),    % que todos pertenezcan a los registrados
      long(Laringoscopia,Length3),  Length3>0, findall(X,prueba(X),List3),   todos_miembros(Laringoscopia,List3),    % que todos pertenezcan a los registrados
      !.

      
diagnostica(Paciente,'Disfonía Orgánica', Sintomas, Signos, Laringoscopia):-
      fumador(Paciente,1),
      %long(Sintomas,Length1),Length1>0,
      findall(X,sintoma(X),List1), todos_miembros(Sintomas,List1),  % que todos pertenezcan a los registrados
      %long(Signos,Length2),  Length2>0,
      findall(X,signo(X),List2),   todos_miembros(Signos,List2),    % que todos pertenezcan a los registrados
      not(miembro('Cambio de coloración de las cuerdas vocales',Laringoscopia)),
      long(Laringoscopia,Length3),  Length3>0, findall(X,prueba(X),List3),   todos_miembros(Laringoscopia,List3),
      !;
      fumador(Paciente,1),
      %long(Sintomas,Length1),Length1>0,
      findall(X,sintoma(X),List1), todos_miembros(Sintomas,List1),  % que todos pertenezcan a los registrados
      %long(Signos,Length2),  Length2>0,
      findall(X,signo(X),List2),   todos_miembros(Signos,List2),    % que todos pertenezcan a los registrados
      miembro('Cambio de coloración de las cuerdas vocales',Laringoscopia),
      long(Laringoscopia,Length3),  Length3>1, findall(X,prueba(X),List3), todos_miembros(Laringoscopia,List3),    % que todos pertenezcan a los registrados
      !.

diagnostica(Paciente,'Posible Disfonía', Sintomas, Signos, Laringoscopia).



%%%%%%%%%% Clasificación según la duración de los sintomas %%%%%%%%%%
clasificacion_tiempo(Dias,'Aguda'):- Dias =< 21, !.
clasificacion_tiempo(Dias,'Crónica').


