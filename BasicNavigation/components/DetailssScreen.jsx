import { Button, Text, View } from "react-native";
import { Button } from 'react-native';

function DetailsScreen({ navigation }) {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Details Screen</Text>
      <Button
        title="Ir para detalhes"
        onPress={() => navigation.navigate("Details")}
      ></Button>
      <Button
        title="Listar filmes"
        onPress={() => navigation.navigate("Movies")}
      ></Button>

    </View>
  );
}
