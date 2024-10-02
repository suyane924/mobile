import React, { useState } from 'react';
import { TextInput, Button, Alert, View, StyleSheet } from 'react-native';

export default function App() {
  const [userNumber, setUserNumber] = useState('');

  const verificarNumero = () => {
    const randomNumber = Math.floor(Math.random() * 11); 
    if (parseInt(userNumber) === randomNumber) {
      Alert.alert("Resultado", "Você acertou!");
    } else {
      Alert.alert("Resultado", `Você errou!`);
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.input}
        keyboardType="numeric"
        placeholder="Digite um número de 0 a 10"
        value={userNumber}
        onChangeText={setUserNumber}
      />
      <Button title="Verificar" onPress={verificarNumero} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 16,
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 12,
    paddingHorizontal: 8,
    borderRadius: 4,
  },
});
